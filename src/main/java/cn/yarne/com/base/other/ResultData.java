package cn.yarne.com.base.other;


/**
 * @author yarne
 * @version 2016年6月21日 上午9:50:58
 */
public class ResultData<T> {

    /*错误码*/
    private Integer code=1;  //默认为1失败，0成功
    /*提示信息*/
    private String msg;
    /*具体的内容*/
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
