package cn.yzw.device.service;

import cn.yzw.device.vo.Device;
import cn.yzw.device.vo.DownItem;
import cn.yzw.device.vo.FixInfo;


public interface DeviceService extends BaseService<Device,Integer> {

    boolean fix(Integer id)throws Exception;

    boolean down(Integer id,DownItem downItem)throws Exception;
    boolean wasFixed(Integer id, FixInfo fixInfo)throws Exception;

}
