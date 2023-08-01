package com.alperen.newnews;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;

public class FirebaseAuthManager {
    private FirebaseAuth firebaseAuth;

    public FirebaseAuthManager() {
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void createUserWithEmailAndPassword(String email, String password,
                                               final OnCompleteListener<AuthResult> onCompleteListener) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(onCompleteListener);
    }

    public void signInWithEmailAndPassword(String email, String password,
                                           final OnCompleteListener<AuthResult> onCompleteListener) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(onCompleteListener);
    }
    public void signOut() {
        firebaseAuth.signOut();
    }
    public FirebaseUser getCurrentUser() {
        return firebaseAuth.getCurrentUser();
    }
}
