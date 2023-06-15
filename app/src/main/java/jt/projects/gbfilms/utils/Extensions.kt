package jt.projects.gbfilms.utils

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable


/**
 * ACTIVITY EXTENSIONS
 */
fun Activity.showSnackbar(text: String) {
    Snackbar.make(
        this.findViewById(android.R.id.content),
        text,
        Snackbar.LENGTH_SHORT
    ).show()
}

fun Activity.showToast(text: String) {
    Toast.makeText(
        this,
        text,
        Toast.LENGTH_SHORT
    ).show()
}


/**
 * FRAGMENT EXTENSIONS
 */
fun Fragment.showSnackbar(text: String) {
    Snackbar.make(
        requireActivity().findViewById(android.R.id.content),
        text,
        Snackbar.LENGTH_SHORT
    ).show()
}

fun Fragment.showSnackbarWithAction(
    text: String,
    actionText: String,
    action: View.OnClickListener
) {
    Snackbar.make(
        requireActivity().findViewById(android.R.id.content),
        text,
        Snackbar.LENGTH_SHORT
    ).setAction(actionText, action).show()
}


// RXJAVA 3
fun Disposable.disposeBy(c: CompositeDisposable) {
    c.add(this)
}