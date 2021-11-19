package fakes;

public class UserValidator {

    private static Database db;
    public UserValidator(){
        this.db = FileDatabase.getInstance();
    }

    public UserValidator(Database db){
        this.db = db;
    }

    public static boolean doesUsernameExist(String username){
        for(User user : db.getUsers()){
            if (user.getUsername().equalsIgnoreCase(username)){
                return true;
            }
        }
        return false;
    }

    public static boolean isValidUsername(String username){
        if(Character.isDigit(username.charAt(0)) ||
                username.equals(null) && !username.chars().allMatch(Character::isLetterOrDigit)){
            return false;
        }
        return true;
    }
}
