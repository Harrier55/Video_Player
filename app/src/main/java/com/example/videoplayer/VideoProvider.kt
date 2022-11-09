package com.example.videoplayer

import android.content.ContentProvider
import android.content.ContentValues
import android.content.res.AssetFileDescriptor
import android.database.Cursor
import android.net.Uri
import android.util.Log
import java.io.FileNotFoundException
import java.io.IOException

class VideoProvider : ContentProvider() {

    @Throws(FileNotFoundException::class)
    override fun openAssetFile(uri: Uri, mode: String): AssetFileDescriptor? {
        Log.d("@@@", "Content Provider")
        val am = context!!.assets
        val file_name = uri.lastPathSegment ?: throw FileNotFoundException()
        var afd: AssetFileDescriptor? = null
        try {
            afd = am.openFd(file_name)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return afd
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
     //   TODO("Implement this to handle requests to delete one or more rows")
        return 0
    }

    override fun getType(uri: Uri): String? {
//        TODO(
//            "Implement this to handle requests for the MIME type of the data" +
//                    "at the given URI"
//        )
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
//        TODO("Implement this to handle requests to insert a new row.")
        return null
    }

    override fun onCreate(): Boolean {
//        TODO("Implement this to initialize your content provider on startup.")
        return false
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
//        TODO("Implement this to handle query requests from clients.")
        return null
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
//        TODO("Implement this to handle requests to update one or more rows.")
        return 0
    }
}