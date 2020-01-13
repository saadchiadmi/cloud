package com.example.cloud.services;

import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FirebaseConnection {
	
	@PostConstruct
	public void Connection() throws IOException {
		FileInputStream serviceAccount = new FileInputStream("./serviceAccount.json");
		
		FirebaseOptions options = new FirebaseOptions.Builder()
		  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
		  .setDatabaseUrl("https://cloud-76b51.firebaseio.com")
		  .build();
		
		FirebaseApp.initializeApp(options);
	}

}
