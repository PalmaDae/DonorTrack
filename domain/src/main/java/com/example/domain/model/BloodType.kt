package com.example.domain.model

enum class BloodType {
    O_PLUS,
    O_MINUS,
    A_PLUS,
    A_MINUS,
    B_PLUS,
    B_MINUS,
    AB_PLUS,
    AB_MINUS;

    companion object {
        fun fromString(value: String): BloodType {
            return when (value) {
                "O+" -> O_PLUS
                "O-" -> O_MINUS
                "A+" -> A_PLUS
                "A-" -> A_MINUS
                "B+" -> B_PLUS
                "B-" -> B_MINUS
                "AB+" -> AB_PLUS
                "AB-" -> AB_MINUS
                else -> A_PLUS
            }
        }

        fun toString(bloodType: BloodType): String {
            return when (bloodType) {
                O_PLUS -> "O+"
                O_MINUS -> "O-"
                A_PLUS -> "A+"
                A_MINUS -> "A-"
                B_PLUS -> "B+"
                B_MINUS -> "B-"
                AB_PLUS -> "AB+"
                AB_MINUS -> "AB-"
            }
        }
    }
}