package com.mleiva.kmpmylistanime

/***
 * Project: KmpMyListAnime
 * From: com.mleiva.kmpmylistanime
 * Creted by: Marcelo Leiva on 29-07-2024 at 10:21
 ***/
import androidx.room.TypeConverter
import com.mleiva.kmpmylistanime.data.Broadcast
import com.mleiva.kmpmylistanime.data.Genre
import com.mleiva.kmpmylistanime.data.Images
import com.mleiva.kmpmylistanime.data.Studio
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

 private val json = Json {
  ignoreUnknownKeys = true
  coerceInputValues = true
 }

 @TypeConverter
 fun fromImages(images: Images): String {
  return json.encodeToString(images)
 }

 @TypeConverter
 fun toImages(imagesString: String): Images {
  return json.decodeFromString(imagesString)
 }

 @TypeConverter
 fun fromBroadcast(broadcast: Broadcast): String {
  return json.encodeToString(broadcast)
 }

 @TypeConverter
 fun toBroadcast(broadcastString: String): Broadcast {
  return json.decodeFromString(broadcastString)
 }

 @TypeConverter
 fun fromGenreList(genres: List<Genre>): String {
  return json.encodeToString(genres)
 }

 @TypeConverter
 fun toGenreList(genresString: String): List<Genre> {
  return json.decodeFromString(genresString)
 }

 @TypeConverter
 fun fromStudioList(studios: List<Studio>): String {
  return json.encodeToString(studios)
 }

 @TypeConverter
 fun toStudioList(studiosString: String): List<Studio> {
  return json.decodeFromString(studiosString)
 }
}
