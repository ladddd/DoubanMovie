package com.ladddd.baselib.utils

import java.io.BufferedReader
import java.io.DataOutputStream

/**
 * Created by 陈伟达 on 2017/12/19.
 */
object ShellUtils {

    data class CommandResult(val result:Int, val successMsg:String?, val errorMsg:String?)

    fun execCmd(command:String, isRoot: Boolean = false, isNeedResultMsg:Boolean = true) : CommandResult {
        return execCmd(Array(1) {command}, isRoot, isNeedResultMsg)
    }

    fun execCmd(commands:Array<String>, isRoot:Boolean = false, isNeedResultMsg:Boolean = true) : CommandResult {
        val result : Int
        var successMsg = ""
        var errorMsg = ""
        val process = Runtime.getRuntime().exec(if (isRoot) "su" else "sh")
        //Closable use 安全进行stream操作 内嵌try-catch
        DataOutputStream(process.outputStream).use {
            os ->
            {
                for (command: String in commands) {
                    os.write(command.toByteArray())
                    os.writeBytes("\n")
                    os.flush()
                }
                os.writeBytes("exit\n")
                os.flush()
            }
        }
        result = process.waitFor()
        if (isNeedResultMsg) {
            successMsg = process.inputStream.bufferedReader().use(BufferedReader::readText)
            errorMsg = process.errorStream.bufferedReader().use(BufferedReader::readText)
        }
        return CommandResult(result, successMsg, errorMsg)
    }
}