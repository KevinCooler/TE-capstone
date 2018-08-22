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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.Objects.User;

@Controller
@SessionAttributes("currentUser")
public class UploadController {
	
	@Autowired
	ServletContext servletContext;

	@RequestMapping(path="/uploadProfilePic", method=RequestMethod.POST)
	public String handleFileUpload(@RequestParam MultipartFile file, ModelMap map,
								HttpServletRequest request, RedirectAttributes redirect,
								HttpSession session) {
		long coachId = Long.parseLong(request.getParameter("coachId"));
		redirect.addFlashAttribute("coachId", coachId);
		User user = (User) session.getAttribute("currentUser");
		
		if(user.getId() != coachId)
			return "redirect:/coach";
		
		File imagePath = getImageFilePath();
		String imageName = imagePath + File.separator + "coach" + coachId;
		
		if (file.isEmpty()) {
			map.addAttribute("message", "File Object empty");
		} else {
			createImage(file, imageName);
		}
		
		return "redirect:/coach";
	}
	
	@RequestMapping(path="/image/{imageName}", method=RequestMethod.GET)
	@ResponseBody
	public byte[] getImage(@PathVariable(value="imageName") String imageName) {
		String imagePath = getServerContextPath() + File.separator + imageName;
		File image = new File(imagePath);
		try {
			return Files.readAllBytes(image.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
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
	
	private void createImage(MultipartFile file, String name) {
		String fileType = FilenameUtils.getExtension(file.getOriginalFilename());
		File image = new File(name);
		byte[] updatedFile = resizePicture(file, fileType);

		try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(image))) {
			stream.write(updatedFile);
		} catch (IOException e) {
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
			ImageIO.write(crop(originalImage), fileType, out );
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