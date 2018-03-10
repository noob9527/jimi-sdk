package cn.staynoob.jimi.domain.response

/**
 * example:
 *     "imei": "868120145233604",
 *     "deviceName": "868120145233604",
 *     "icon": "bus",
 *     "status": "0",
 *     "posType": "GPS",
 *     "lat": 22.577282,
 *     "lng": 113.916604,
 *     "hbTime": "2017-04-26 09:14:50",
 *     "accStatus": "0",
 *     "speed": "0",
 *     "gpsTime": "2017-04-26 09:17:46",
 *     "activationFlag": "1",
 *     "expireFlag": "1",
 *     "electQuantity": "60",
 *     "locDesc": null,
 *     "powerValue": null
 */
data class DeviceLocation(
        val imei: String,
        val deviceName: String,
        val status: Int,
        val lng: Double,
        val lat: Double,
        val locDesc: String?,
        val speed: Int,
        val electQuantity: Int
) {
}