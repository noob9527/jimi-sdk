package cn.staynoob.jimi.domain.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

/**
 * example:
 *     "lat": 22.577144898887813,
 *     "lng": 113.91674845964586,
 *     "gpsTime": "2017-04-26 00:00:58",
 *     "direction": 0,
 *     "gpsSpeed": -1,
 *     "posType": 3
 */
data class TrackPoint(
        val lng: Double,
        val lat: Double,
        @get:JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd HH:mm:ss"
        )
        val gpsTime: LocalDateTime,
        val direction: Int,
        val gpsSpeed: Int,
        val posType: Int
) {
}