public class 等差数列 {
    int 首项;
    int 公差;
    int 项数;

    public 等差数列(int 首项, int 公差, int 项数) {
        this.首项 = 首项;
        this.公差 = 公差;
        this.项数 = 项数;
    }

    public int 求和(){
        int sum=0;
        for(int i=0;i<项数;i++){
            sum=sum+首项+(i*公差);
        }
        return sum;
    }

    public float 求平均值(){
        return (float)求和()/项数;
    }
}
