package cn.staynoob.jimi.domain.response

/**
 * example:
 *     "imei": "868120145233604",
 *     "deviceName": "868120145233604",
 *     "mcType": "GT300L",
 *     "mcTypeUseScope": "personal",
 *     "sim": "415451",
 *     "expiration": "2037-04-01 23:59:59",
 *     "activationTime": "2017-04-01 11:02:20",
 *     "reMark": "test",
 *     "vehicleName": null,
 *     "vehicleIcon": "bus",
 *     "vehicleNumber": "粤B3604",
 *     "vehicleModels": null,
 *     "carFrame": "2235",
 *     "driverName": "driver",
 *     "driverPhone": "13825036579",
 *     "enabledFlag": 1,
 *     "engineNumber": "8565674"
 */
data class Device(
        val imei: String,
        val deviceName: String,
        val status: Int
)
