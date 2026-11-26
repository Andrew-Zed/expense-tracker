package com.andrew.expensetracker;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseConfig {

    /**
     * Initializes Firebase.
     */
    public static void initializeFirebase() throws IOException {
        try (FileInputStream serviceAccount =
                     new FileInputStream("firebase/service-account.json")) {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(
                            GoogleCredentials.fromStream(
                                    serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("Firebase initialized successfully.");
            }
        }
    }

    /**
     * Returns Firestore instance.
     */
    public static Firestore getFirestore() {

        return FirestoreClient.getFirestore();

    }

}
