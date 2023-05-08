package com.gazim.minecraft_plugin.lightweightbackupworldplugin.world_git_backup

import com.gazim.minecraft_plugin.lightweightbackupworldplugin.extension.sendStrMessage
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.PersonIdent
import org.eclipse.jgit.revwalk.RevCommit
import java.io.File
import java.util.*

object GitWorldBackup {

    private const val backupsFolder = "light_weight_backups"
    private const val worldBackupPrefix = "git_"

    private fun getGit(targetDirectory: String) =
        Git.init().setDirectory(File(targetDirectory)).call()

    fun save(worldType: WorldType) {
        val world = worldType.value
        val targetDirectory = "./$backupsFolder/$worldBackupPrefix$world"
        File("./$world").copyRecursively(
            File("$targetDirectory/$world").apply {
                deleteRecursively()
            }
        )
        val git = getGit(targetDirectory)
        git.add().addFilepattern(".").call()
        git.commit().apply {
            author = PersonIdent("LightWeightBackupWorldPlugin", "@")
            setAll(true)
            message = "${Date()}"
        }.call()
    }

    fun list(worldType: WorldType): List<String> = runCatching {
        getGit("./$backupsFolder/$worldBackupPrefix${worldType.value}")
            .log().call().map(RevCommit::getFullMessage)
    }.getOrNull() ?: emptyList()

}