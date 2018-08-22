package com.techelevator.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.Objects.User;

@Controller
@SessionAttributes("currentUser")
public class UploadController {
	
	@Autowired
	ServletContext servletContext;

	@RequestMapping(path="/uploadProfilePic", method=RequestMethod.POST)
	public String handleFileUpload(@RequestParam MultipartFile file, 
			@RequestParam long coachId, RedirectAttributes redirect,
			HttpSession session) {
		redirect.addFlashAttribute("coachId", coachId);
		User user = (User)session.getAttribute("currentUser");
		
		if(user != null) {
			if(user.getId() != coachId)
				return "redirect:/coach";
			
			String errorMessage = fileCheck(file);
			
			if(errorMessage != null) {
				redirect.addFlashAttribute("errorMessage", errorMessage);
				return "redirect:/editCoach";
			}
			
			createImageFile(file, coachId);
		}
		
		return "redirect:/coach";
	}
	
	@RequestMapping(path="/deleteProfilePic", method=RequestMethod.POST)
	public String deletePicture(@RequestParam long coachId, 
			RedirectAttributes redirect) {
		String imagePath = getServerContextPath() + File.separator + "coach" + coachId;
		File image = new File(imagePath);
		
		if(image.exists())
			image.delete();
		
		redirect.addFlashAttribute("coachId", coachId);
		
		return "redirect:/editCoach";
	}
	
	@RequestMapping(path="/image/{imageName}", method=RequestMethod.GET)
	@ResponseBody
	public byte[] getImage(@PathVariable(value="imageName") String imageName) {
		String imagePath = getServerContextPath() + File.separator + imageName;
		File image = new File(imagePath);
		
		if(image.exists()) {
			try {
				return Files.readAllBytes(image.toPath());
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		return null;
	}
	
	private String fileCheck(MultipartFile file) {
		if(file.isEmpty())
			return "No file was selected";
		
		if(file.getSize() > 1048576) // If file is over 1MB
			return "File size must be under 1MB";
		
		if(!file.getContentType().contains("image"))
			return "Only image files can be uploaded (jpg, gif, png)";
		
		return null;
	}
	
	private void createImageFile(MultipartFile file, long coachId) {
		File imagePath = getImageFilePath();
		String imageName = imagePath + File.separator + "coach" + coachId;
		
		createImage(file, imageName);
	}
	
	private File getImageFilePath() {
		String serverPath = getServerContextPath();
		File filePath = new File(serverPath);
		
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		
		return filePath;
	}
	
	private String getServerContextPath() {
		return servletContext.getRealPath("/") + "uploads";
	}
	
	private void createImage(MultipartFile file, String imageName) {
		String fileType = FilenameUtils.getExtension(file.getOriginalFilename());
		File image = new File(imageName);
		byte[] updatedFile = resizePicture(file, fileType);

		try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(image))) {
			stream.write(updatedFile);
		} catch (IOException | MaxUploadSizeExceededException e) {
			e.printStackTrace();
		}
	}
	
	// I heavily relied upon these tutorials to set this crop functionality up:
	// http://blog.netgloo.com/2015/03/03/spring-boot-crop-uploaded-image/
	// https://www.mkyong.com/java/how-to-convert-bufferedimage-to-byte-in-java/
	private byte[] resizePicture(MultipartFile file, String fileType) {
		byte[] updatedPic = null;
		
		try (InputStream in = new ByteArrayInputStream(file.getBytes())) {
			BufferedImage originalImage = ImageIO.read(in);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ImageIO.write(crop(originalImage), fileType, out);
			out.flush();
			updatedPic = out.toByteArray();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return updatedPic;
	}
	
	private BufferedImage crop(BufferedImage originalImage) {
		int height = originalImage.getHeight();
		int width = originalImage.getWidth();
		int croppedHeight = 0;
		int croppedWidth = 0;
		int squareSize = 0;
		
		if(height == width)
			return originalImage;
		
		if(height > width) {
			croppedHeight = (height - width) / 2;
			squareSize = width;
		} else {
			croppedWidth = (width - height) / 2;
			squareSize = height;
		}
		
		return originalImage.getSubimage(croppedWidth, croppedHeight, squareSize, squareSize);
	}
}