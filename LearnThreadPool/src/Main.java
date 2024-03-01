import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        for(int i=0;i<8;i++){
            executorService.submit(new PrintHelloWorld(i+""));
        }

        for(int i=0;i<8;i++){
            int finalI = i;
            executorService.submit(()->{
                System.out.println(finalI +":GoodbyeWorld!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
class PrintHelloWorld implements Runnable{
    private String name;
    public PrintHelloWorld(String name){
        this.name=name;
    }

    @Override
    public void run() {
        System.out.println(name+":HelloWorld!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

