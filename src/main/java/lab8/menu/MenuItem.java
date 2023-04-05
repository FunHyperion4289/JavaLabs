package lab8.menu;

public class MenuItem {

    private String name;
    Runnable menuCall;

    public MenuItem(String name, Runnable menuCall) {

        this.name = name;
        this.menuCall = menuCall;
    }


    public String getName() {

        return this.name;
    }

    public void runMethod(){

        this.menuCall.run();
    }


}