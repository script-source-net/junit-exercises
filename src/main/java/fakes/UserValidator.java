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
            if (user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public static boolean isValidUsername(String username){
        if(Character.isDigit(username.charAt(0))){
            return false;
        } else if(username.equals(null)){
            return false;
        } else if(!username.chars().allMatch(Character::isLetterOrDigit)){
            return false;
        }
        return true;
    }
}
