package fakes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class UserValidatorTest {

    /**
     * Alle Tests in der folgenden Klasse isValidUsername sollen die Methode UserValidator.isValidUsername testen.
     * Damit alle Tests grün werden, musst du die Implementation von UserValidator.isValidUsername anpassen!
     */
    @Nested
    class isValidUsername {

        @Test
        void returnsTrueIfOnlyLetters() {
            //Username muss keine Digits oder alphanumerische Zeichen beinhalten
            Assertions.assertTrue(new UserValidator().isValidUsername("jhonnyd"));
        }

        @Test
        void returnsFalseIfStartsWithNumber(){
            //Username darf nicht mit Digit beginnen
            Assertions.assertFalse(new UserValidator().isValidUsername("1jhonnyd"));
        }

        @Test
        void returnsTrueIfContainsNumberButNotAsFirstChar() {
            //Benötigt keine spezielle Kondition im Code, da nur das erste Zeichen kein Digit sein darf
            Assertions.assertTrue(new UserValidator().isValidUsername("jhonny3d"));
        }

        @Test
        void returnsFalseIfContainsAnyNonAlphanumericChar() {
            //Username darf kein alphanumerisches Zeichen beinhalten
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
            Database db = new FakeDatabase();
            UserValidator uv = new UserValidator(db);
            boolean usernameExist = uv.doesUsernameExist("peter");
            Assertions.assertFalse(usernameExist);
        }

        @Test
        void returnsFalseIfUsernameNotInDBYet__MOCKITO() {
            Database db = mock(Database.class);
            final UserValidator uv = new UserValidator(db);
            boolean usernameExist = uv.doesUsernameExist("peter");
            Assertions.assertFalse(usernameExist);
        }

        @Test
        void returnsTrueIfUsernameInDB__FAKE() {
            Database db = new FakeDatabase();
            UserValidator uv = new UserValidator(db);
            db.addUser(new User("peter"));
            boolean usernameExist = uv.doesUsernameExist("peter");
            Assertions.assertTrue(usernameExist);
        }

        @Test
        void returnsTrueIfUsernameInDB__MOCKITO() {
            Database db = mock(Database.class);
            final UserValidator uv = new UserValidator(db);
            doReturn(Arrays.asList(new User("peter"))).when(db).getUsers();
            boolean usernameExist = uv.doesUsernameExist("peter");
            Assertions.assertTrue(usernameExist);
        }

        @Test
        void returnsTrueIfSameNameInDBButWithDifferentLetterCasing() {
            // TODO implement test
            Database db = new FakeDatabase();
            UserValidator uv = new UserValidator(db);
            db.addUser(new User("peter"));
            boolean usernameExist = uv.doesUsernameExist("peTer");
            Assertions.assertTrue(usernameExist);
        }
    }
}
