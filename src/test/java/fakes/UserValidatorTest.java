package fakes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class UserValidatorTest {

    /**
     * Alle Tests in der folgenden Klasse isValidUsername sollen die Methode UserValidator.isValidUsername testen.
     * Damit alle Tests grün werden, musst du die Implementation von UserValidator.isValidUsername anpassen!
     */
    @Nested
    class isValidUsername {

        @Test
        void returnsTrueIfOnlyLetters() {
            Assertions.assertTrue(new UserValidator().isValidUsername("jhonnyd"));
        }

        @Test
        void returnsFalseIfStartsWithNumber(){
            Assertions.assertFalse(new UserValidator().isValidUsername("1jhonnyd"));
        }

        @Test
        void returnsTrueIfContainsNumberButNotAsFirstChar() {
            Assertions.assertTrue(new UserValidator().isValidUsername("jhonny3d"));
        }

        @Test
        void returnsFalseIfContainsAnyNonAlphanumericChar() {
            Assertions.assertFalse(new UserValidator().isValidUsername("jhonny!d"));
        }
    }

    /**
     * Alle Tests in der folgenden Klasse doesUsernameExist sollen die Methode UserValidator.doesUsernameExist testen.
     * Damit alle Tests grün werden, musst du die Implementation von UserValidator.doesUsernameExist anpassen!
     */
    static class doesUsernameExist {
        @Test
        void returnsFalseIfUsernameNotInDBYet__FAKE() {
            // TODO implementiere / ergänze den Test hier, so dass dieser kompiliert und grün ist.
            Database db = new FakeDatabase();
            UserValidator uv = new UserValidator(db);
            boolean usernameExist = uv.doesUsernameExist("peter");
            Assertions.assertFalse(usernameExist);
        }

        @Test
        void returnsFalseIfUsernameNotInDBYet__MOCKITO() {
            // TODO implement test
        }

        @Test
        void returnsTrueIfUsernameInDB__FAKE() {
            // TODO implementiere / ergänze den Test hier, so dass dieser kompiliert und grün ist.

            // boolean usernameExist = uv.doesUsernameExist("peter");

            // Assertions.assertTrue(usernameExist);
        }

        @Test
        void returnsTrueIfUsernameInDB__MOCKITO() {
            // TODO implement test
        }

        @Test
        void returnsTrueIfSameNameInDBButWithDifferentLetterCasing() {
            // TODO implement test
        }
    }
}
