package club.haozheng.restaurant.comom;

public class StatusCode {
    public static final int ok = 20000;//申请成功
    public static final int error = 20001;//系统错误
    public static final int parameter = 20002;//解析错误
    public static final int reSubmision = 20003;//重复提交
    public static final int noExist = 20005;//结果不存在
    public static final int noPower = 20004;//无权限
    public static final int noRegister = 30000;//设备未注册
    public static final int secretError = 30001;//密钥错误
    public static final int userNoPower = 30002;//用户无权限
    public static final int systemError = 30003;//系统错误
}
