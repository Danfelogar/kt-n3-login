package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.core.helpers

import android.util.Patterns

fun interface ValidationRule {
    fun validate(value: String): String?
}

object ValidationRules {
    //constants
    val EMAIL = ValidationRule { value ->
        if (value.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
           "The email is not valid"
        } else null
    }

    val REQUIRED = ValidationRule { value ->
        if (value.isEmpty()) "This fields is required" else null
    }

    // functions
    fun MIN_LENGTH(min: Int) = ValidationRule { value ->
        if (value.isNotEmpty() && value.length < min) {
            "The minimum length is $min characters"
        } else null
    }

    fun MAX_LENGTH(max: Int) = ValidationRule { value ->
        if (value.isNotEmpty() && value.length > max) {
            "The maximum length is $max characters"
        } else null
    }

    val REQUIRES_DIGIT = ValidationRule { value ->
        if (value.isNotEmpty() && !value.any { it.isDigit() }) {
            "Must contain at least one digit"
        } else null
    }

    val REQUIRES_UPPERCASE = ValidationRule { value ->
        if (value.isNotEmpty() && !value.any { it.isUpperCase() }) {
            "Must contain at least one uppercase letter"
        } else null
    }

    val REQUIRES_SPECIAL_CHAR = ValidationRule { value ->
        val specialChars = "!@#$%^&*()_+"
        if (value.isNotEmpty() && !value.any { it in specialChars }) {
            "Must contain at least one special character ($specialChars)"
        } else null
    }

    fun MATCHES(otherValue: () -> String) = ValidationRule { value ->
        if (value.isNotEmpty() && value != otherValue()) {
            "The fields don't match"
        } else null
    }

    fun CUSTOM(errorMessage: String, condition: (String) -> Boolean) = ValidationRule { value ->
        if (value.isNotEmpty() && !condition(value)) errorMessage else null
    }
}

class CompositeValidator(private val rules: List<ValidationRule>) {
    fun validate(value: String): String? {
        for (rule in rules) {
            rule.validate(value)?.let { return it }
        }
        return null
    }
}

fun validator(vararg rules: ValidationRule): CompositeValidator {
    return CompositeValidator(rules.toList())
}

//example use case
//validator(
//ValidationRules.MIN_LENGTH(8),
//ValidationRules.REQUIRES_DIGIT,
//ValidationRules.REQUIRES_UPPERCASE,
//ValidationRules.REQUIRES_SPECIAL_CHAR
//)