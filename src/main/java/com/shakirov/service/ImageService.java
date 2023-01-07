package com.shakirov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

/**
 * @author igor@shakirov-dev.ru on 05.01.2023
 * @version 1.0
 */

@Service
@RequiredArgsConstructor
public class ImageService {
	
	@Value("${app.image.bucket:I:\\Desktop\\Programming\\spring-up\\spring-up\\image}")
	private final String bucket;
	
//	@SneakyThrows
	public void upload(String imagePath, InputStream content) {
		Path fullImagePath = Path.of(bucket, imagePath);
		
		try (content) {
			// HardCode
			try {
				Files.createDirectory(fullImagePath.getParent());
			} catch (FileAlreadyExistsException f) {
			}
			Files.write(fullImagePath, content.readAllBytes(),
					StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
//	@SneakyThrows
	public Optional<byte[]> get(String imagePath) {
		Path fullImagePath = Path.of(bucket, imagePath);
		
		try {
			return Files.exists(fullImagePath) ?
					Optional.of(Files.readAllBytes(fullImagePath))
					: Optional.empty();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
