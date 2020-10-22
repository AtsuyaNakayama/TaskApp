package jp.techacademy.atsuya.taskapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.View
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_category_add.*
import java.util.*

class CategoryAddActivity : AppCompatActivity() {
    private lateinit var mRealm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_add)

        // Realmの設定
        mRealm = Realm.getDefaultInstance()

        add_button.setOnClickListener ( { view: View? ->
            val editText = edit_category_text.text.toString()

            if (editText != "") {
                val category = Category()
                category.category = editText
                mRealm.beginTransaction()

                val taskRealmResults = mRealm.where(Category::class.java).findAll()

                val identifier: Int =
                    if (taskRealmResults.max("id") != null) {
                        taskRealmResults.max("id")!!.toInt() + 1
                    } else {
                        1
                    }
                category.id = identifier
                mRealm.copyToRealmOrUpdate(category)
                mRealm.commitTransaction()

                edit_category_text.setText("")
                Snackbar.make(view!!, "追加しました。", Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(view!!, "文字を入力してください。", Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mRealm.close()
    }
}
