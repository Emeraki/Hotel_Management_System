package com.yh.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yh.pojo.RoomType;
import com.yh.service.RoomTypeService;
import com.yh.utils.DataGridViewResult;
import com.yh.utils.SystemConstant;
import com.yh.utils.UUIDUtils;
import com.yh.vo.RoomTypeVo;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.type.SimpleTypeRegistry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/roomType")
public class RoomTypeController {

    @Resource
    private RoomTypeService roomTypeService;

    /**
     * 获得房屋类型列表
     * @param roomTypeVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(RoomTypeVo roomTypeVo){

        //设置分页信息
        PageHelper.startPage(roomTypeVo.getPage(),roomTypeVo.getLimit());

        //调用查询方法
        List<RoomType> roomTypeList = roomTypeService.findRoomTypeList(roomTypeVo);

        //创建分页对象
        PageInfo<RoomType> pageInfo = new PageInfo<RoomType>(roomTypeList);

        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 上传房屋图片
     * @return
     */
    @RequestMapping("/uploadFile")
    public String uploadFile(MultipartFile file){

        System.out.println("====================");
        System.out.println("666");
        System.out.println("====================");

        //创建Map集合保存返回的JSON数据
        Map<String,Object> map = new HashMap<String,Object>();
        //判断是否有选中文件
        if(!file.isEmpty()){
            String path = "E:/hotel_image/";
            //获取源文件的名称
            String oldFileName = file.getOriginalFilename();
            //获取文件的后缀名
            String extension = FilenameUtils.getExtension(oldFileName);
            //重命名旧文件
            String newFileName = UUIDUtils.randomUUID()+"."+extension;
            //为了解决同一个文件夹下文件过多的问题，使用日期作为文件夹管理
            String datePath = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            //组装最终的文件名
            String finalName = datePath + "/" + newFileName;
            //创建文件对象
            //参数1：文件上传的地址  参数2：文件名称
            File dest = new File(path,finalName);
            //判断该文件夹是否存在，不存在则创建文件夹
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();//创建文件夹
            }

            try {
                //进行文件上传
                file.transferTo(dest);
                map.put("code",0);//状态码
                map.put("msg","上传成功");//执行消息
                Map<String,Object> dataMap = new HashMap<String,Object>();
                dataMap.put("src","/hotel/show/"+finalName);
                map.put("data",dataMap);//文件数据
                map.put("imagePath",finalName);//隐藏域的值，只保留日期文件夹+重命名后的文件名

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println("===================");
        System.out.println(map);
        System.out.println("===================");
        return JSON.toJSONString(map);
    }

    /**
     * 添加房型信息
     * @param roomType
     * @return
     */
    @RequestMapping("/addRoomType")
    public String addRoomType(RoomType roomType){
        Map<String, Object> map = new HashMap<String, Object>();

        if (roomTypeService.addRoomType(roomType) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"添加房型成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"添加房型失败");
        }

        return JSON.toJSONString(map);
    }

    /**
     * 修改房型信息
     * @param roomType
     * @return
     */
    @RequestMapping("/updateRoomType")
    public String updateRoomType(RoomType roomType){
        Map<String, Object> map = new HashMap<String, Object>();

        if (roomTypeService.updateRoomType(roomType) > 0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"修改房型成功");
        }else {
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"修改房型失败");
        }

        return JSON.toJSONString(map);
    }

    /**
     * 主要适用于搜索框里面的查询所有房间类型名字
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(){
        return JSON.toJSONString(roomTypeService.findRoomTypeList(null));
    }
}
