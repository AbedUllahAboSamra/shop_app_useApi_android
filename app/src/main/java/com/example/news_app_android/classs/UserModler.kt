package com.example.news_app_android.classs

class UserModle(
    var uid: String,
    var userName: String,
    var password: String,
    var email: String,
    var imageUrl: String
) {

    companion object {
       var userModle =UserModle("","","","","")
    }

    fun toJson(): HashMap<String, Any> {
        var map = HashMap<String, Any>()
        map.put("uid", uid)
        map.put("userName", userName)
        map.put("password", password)
        map.put("imageUrl", imageUrl)
        map.put("email", email)

        return map
    }


}