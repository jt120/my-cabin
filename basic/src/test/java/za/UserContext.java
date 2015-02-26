package za;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ze.liu on 2014/7/15.
 */
public class UserContext {

    String name;

    private List<Interest> interestList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Interest> getInterestList() {
        return interestList;
    }

    public void setInterestList(List<Interest> interestList) {
        this.interestList = interestList;
    }

    public UserContext(String name, List<Interest> interestList) {
        this.name = name;
        this.interestList = interestList;
    }

    @Override
    public String toString() {
        return "UserContext{" +
                "name='" + name + '\'' +
                ", interestList=" + interestList +
                '}';
    }

    public static void main(String[] args) {
        Interest interest = new Interest("football","11");
        List<Interest> list = new ArrayList<Interest>();
        list.add(interest);
        UserContext u = new UserContext("zhang", list);
        System.out.println(u+"");

    }
}
