package com.example.cloud.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.example.cloud.entities.Book;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirebaseService {
	
	public Book createBook(Book book) throws InterruptedException, ExecutionException {
		Firestore db = FirestoreClient.getFirestore();
		DocumentReference docRef = db.collection("books").document(book.getName());
		ApiFuture<DocumentSnapshot> future = docRef.get();
		DocumentSnapshot document = future.get();
		if (document.exists()) {
			return null;
		} else {
			docRef.set(book);
			return book;
		}
	}
	
	public List<Book> getBooks() throws InterruptedException, ExecutionException {
		Firestore db = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> future = db.collection("books").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		List<Book> book = new ArrayList<Book>();
		if (!documents.isEmpty()) {
			for (DocumentSnapshot document : documents) {
				book.add(document.toObject(Book.class));
			}
			return book;
		} else {
		  System.out.println("No such document!");
		  return null;
		}
	}
	
	public Book getBookByName(String name) throws InterruptedException, ExecutionException {
		Firestore db = FirestoreClient.getFirestore();
		DocumentReference docRef = db.collection("books").document(name);
		ApiFuture<DocumentSnapshot> future = docRef.get();
		DocumentSnapshot document = future.get();
		Book book = null;
		if (document.exists()) {
			book = document.toObject(Book.class);
			System.out.println(book);
			return book;
		} else {
		  System.out.println("No such document!");
		  return null;
		}
	}
	
	public Book deleteUser(String name) throws InterruptedException, ExecutionException {
		Firestore db = FirestoreClient.getFirestore();
		DocumentReference docRef = db.collection("books").document(name);
		ApiFuture<DocumentSnapshot> future = docRef.get();
		DocumentSnapshot document = future.get();
		Book book = null;
		if (document.exists()) {
			book = document.toObject(Book.class);
			docRef.delete();
			return book;
		} else {
		  System.out.println("No such document!");
		  return null;
		}
	}

}
