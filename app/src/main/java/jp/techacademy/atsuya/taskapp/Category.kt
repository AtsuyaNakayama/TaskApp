package jp.techacademy.atsuya.taskapp


import java.io.Serializable
import java.util.Date
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Category : RealmObject(), Serializable {
    var category: String = ""  // カテゴリー

    // id をプライマリーキーとして設定
    @PrimaryKey
    var id: Int = 0
}
