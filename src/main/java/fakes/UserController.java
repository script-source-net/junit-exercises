package fakes;

public class UserController {

    private static Database db;

    public UserController(){
        this.db = FileDatabase.getInstance();;
    }

    public UserController(UserValidator uv){

    }

    public Message create(User user){
        if(user == null){
            throw new IllegalArgumentException("user required");
        }
        Boolean canCreate = UserValidator.isValidUsername(user.getUsername())
                            && !UserValidator.doesUsernameExist(user.getUsername());
        if(canCreate){
            db.addUser(user);
            return Message.createOK();
        }else{
            return Message.createNotOK();
        }
    }
}
