package fakes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserControllerTest {

  // Pro getestete Methode gibt es eine inner class (Hier für UserController.create)
  @Nested
  class create {

    // --- Testing with Fakes ---

    @Test
    @org.junit.jupiter.api.Disabled
    void withValidInexistingUsername_returnsOK__NO_FAKE_DEMO() {
      // Arrange
      UserController ctrl = new UserController();
      User user = new User("kalua");

      // Act
      Message result = ctrl.create(user);

      // Assert
      Assertions.assertEquals(result.status, Message.Status.OK);
    }

    @Test
    void withValidInexistentUsername_returnsOK__FAKE() {
      // 1. Test schneller machen
      // 2. UserController.create so beeinflussen,
      //    dass einmal der "if"- und einmal der "else"-Fall durchlaufen wird

      // Tipp: Welche Methode sorgt dafür, dass canCreate im UserController true bzw. false ist?
      //  Kannst du deren Rückgabetyp beeinflussen?

      // Die Schritte die du ausprogrammieren musst:
      // 1. Arrange: UserController mit allen Abhängigkeiten initialisieren
      // 2. Act: UserController.create Methode aufrufen
      // 3. Assert: Rückgabewert von UserController.create prüfen

      // TODO implement test
      UserValidator userValidator = new FakeUserValidator(false,true);
      UserController controller = new UserController(userValidator);
      User user = new User("kalua");
      Message result = controller.create(user);
      Assertions.assertEquals(Message.Status.OK,result.status);
    }

    @Test
    void withValidInexistentUsername_returnsOK__MOCKITO() {
      UserValidator userValidator = mock(UserValidator.class);
      when(userValidator.isValidUsername(anyString())).thenReturn(true);
      when(userValidator.doesUsernameExist(anyString())).thenReturn(false);

      UserController controller = new UserController(userValidator);
      User user = new User("kalua");
      Message result = controller.create(user);
      Assertions.assertEquals(Message.Status.OK,result.status);
    }

    @Test
    void withValidInexistentUsername_shouldAddUserToDB__FAKE() {
      // Der Test soll prüfen, ob der Benutzer tatsächlich der DB hinzugefügt wurde.
      // Dazu soll ein Mock-Objekt für die Database Klasse verwendet werden.

      // TODO implement test
      // Tipp: Wie kann dein Test feststellen, ob der UserController der Datenbank einen Benutzer hinzugefügt hat?
      //   Welche Art von Fake (Stub oder Mock) kann dir weiterhelfen?

      UserValidator userValidator = new FakeUserValidator(false,true);
      Database db = new FakeDatabase();
      UserController controller = new UserController(userValidator, db);
      controller.create(new User("a"));
      Assertions.assertEquals("a",db.getUsers().get(0).getUsername());
    }

    @Test
    void withValidInexistentUsername_shouldAddUserToDB__MOCKITO() {
      // Der Test soll prüfen, ob der Benutzer tatsächlich der DB hinzugefügt wurde.
      // Dazu soll ein Mock-Objekt für die Database Klasse verwendet werden.

      // Tipp: Du kannst prüfen, ob der User hinzugefügt wurde,
      //  indem du prüfst wie of die Methode Database.addUser aufgerufen wurde.
      UserValidator userValidator = mock(UserValidator.class);
      when(userValidator.isValidUsername(anyString())).thenReturn(true);
      when(userValidator.doesUsernameExist(anyString())).thenReturn(false);

      Database db = mock(FakeDatabase.class);
      UserController controller = new UserController(userValidator,db);
      controller.create(new User("a"));
      verify(db, times(1)).addUser(any(User.class));
    }

    // --- Testing Exceptions ---

    @Test
    void withNullUser_throwsIllegalArgumentExc__TRY_CATCH() {
      try {
        UserController ctrl = new UserController();
        ctrl.create(null);
        Assertions.fail("No IllegalArgumentException was thrown");
      } catch (IllegalArgumentException ex) {
        // Optional: Test message
        Assertions.assertEquals("user required", ex.getMessage());
      }
    }

    @Test
    void withNullUser_throwsIllegalArgumentException__THROWN() {
      Assertions.assertThrows(IllegalArgumentException.class, () -> {
        UserController ctrl = new UserController();
        ctrl.create(null);
      });
    }

    @Test
    void withNullUser_throwsIllegalArgumentExceptionWithMessage__THROWN_MESSAGE() {
      Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
        UserController ctrl = new UserController();
        ctrl.create(null);
      });
      Assertions.assertTrue(thrown.getMessage().contains("required"));
    }
  }
}
