package com.kotlin.androidproject.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.kotlin.androidproject.R
import com.kotlin.androidproject.data.Course
import com.kotlin.androidproject.data.dataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(dataUiState())
    val uiState:StateFlow<dataUiState> = _uiState.asStateFlow()
    private fun showToast(context: Context, message:String,course: Course){
        Log.d("cliable",message+course.name)
        Toast.makeText(context,message+ course.name, Toast.LENGTH_SHORT).show()
    }
    fun setCourse(context: Context,course: Course){
        _uiState.value = dataUiState(course)
//        showToast(context,"Course selected",course)
    }

    fun openCourse(context: Context,
                   url:String){
        val intent = Intent(Intent.ACTION_SEND).apply{
            type="text/plain"
            putExtra(Intent.EXTRA_SUBJECT,url)
            putExtra(Intent.EXTRA_TEXT,_uiState.value.selectedCourse.URL)
        }
       context.startActivity(
           Intent.createChooser(
               intent,
               context.getString(R.string.share_course)
           )
       )
        Log.d("ViewModel","shareCourse")
    }



}