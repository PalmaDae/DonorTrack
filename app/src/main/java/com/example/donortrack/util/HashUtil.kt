package com.example.donortrack.util

import org.mindrot.jbcrypt.BCrypt

fun hashpass(
    pass: String
): String {
    val salt = BCrypt.gensalt(16);

    return BCrypt.hashpw(pass, salt)
}

fun verify(
    pass: String,
    hashPass: String
): Boolean {
    return BCrypt.checkpw(pass,hashPass)
}