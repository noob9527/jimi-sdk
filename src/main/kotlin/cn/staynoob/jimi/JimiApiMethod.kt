package cn.staynoob.jimi

enum class JimiApiMethod(val value: String) {
    //认证API
    OAUTH_TOKEN_GET("jimi.oauth.token.get"),    //获取access_token
    OAUTH_TOKEN_REFRESH("jimi.oauth.token.refresh"),    //刷新access_token
    //用户API
    USER_CHILD_CREATE("jimi.user.child.create"),    //为指定账号创建子账号
    USER_CHILD_LIST("jimi.user.child.list"),    //查询账号下的所有子账户信息
    USER_DEVICE_LIST("jimi.user.device.list"),    //查询账户下所有设备信息
    USER_DEVICE_LOCATION_LIST("jimi.user.device.location.list"),    //查询账户下所有设备最新位置信息
    //设备API
    DEVICE_LOCATION_GET("jimi.device.location.get"),    //根据IMEI获取最新定位数据
    DEVICE_TRACK_LIST("jimi.device.track.list"),    //根据IMEI获取轨迹数据
    OPEN_DEVICE_GETDETAILS("jimi.open.device.getDetails"),    //根据IMEI获取设备详细信息
    OPEN_DEVICE_UPDATE("jimi.open.device.update"),    //为设备IMEI修改车辆信息
    OPEN_DEVICE_MOVE("jimi.open.device.move"),    //转移设备
    //指令API
    OPEN_INSTRUCTION_LIST("jimi.open.instruction.list"),    //获取设备支持的指令列表
    OPEN_INSTRUCTION_SEND("jimi.open.instruction.send"),    //指定设备发送指令
    OPEN_INSTRUCTION_RESULT("jimi.open.instruction.result"),    //获取设备指令结果
    //围栏API
    OPEN_DEVICE_FENCE_CREATE("jimi.open.device.fence.create"),    //为设备imei创建电子围栏
    OPEN_DEVICE_FENCE_DELETE("jimi.open.device.fence.delete"),    //为设备imei删除电子围栏
    OPEN_DEVICE_FENCE_GET("jimi.open.device.fence.get"),    //获取设备电子围栏信息
    //LBS-API
    LBS_ADDRESS_GET("jimi.lbs.address.get"),    //wifi、基站定位解析
    //消息服务
    PUSH_DEVICE_ALARM("jimi.push.device.alarm");    //推送设备告警信息

    override fun toString(): String {
        return value
    }
}