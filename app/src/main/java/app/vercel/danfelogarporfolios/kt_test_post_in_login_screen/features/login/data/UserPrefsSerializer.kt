package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.features.login.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.AuthData
import java.io.InputStream
import java.io.OutputStream

object UserPrefsSerializer: Serializer<AuthData> {
    override val defaultValue: AuthData = AuthData.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): AuthData {
        try{
            return AuthData.parseFrom(input)
        } catch (e: Exception) {
            throw CorruptionException("Can't read proto", e)
        }
    }

    override suspend fun writeTo(t: AuthData, output: OutputStream){
        t.writeTo(output)
    }
}