import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<shop> shopList=new ArrayList<>();
        shop shop_1=new shop("phone",6499);
        shop shop_2=new shop("water",1);
        String name=shop_1.getName();
        shopList.add(shop_1);
        shopList.add(shop_2);
        shopList.add(shop_2);

        for(int i=0;i<shopList.size();i++){
            System.out.println(shopList.get(i).name);
        }
    }
}