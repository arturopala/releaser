/*
 * Copyright 2015 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.releaser

trait PathBuilder {

  def jarFilenameFor(v: VersionDescriptor): String

  def jarUrlFor(v: VersionDescriptor): String

  def jarUploadFor(v: VersionDescriptor): String

  def pomUploadFor(v: VersionDescriptor): String

  def pomFilenameFor(v: VersionDescriptor): String

  def pomUrlFor(v: VersionDescriptor): String
}

//https://bintray.com/artifact/download/hmrc/sbt-plugin-release-candidates/uk.gov.hmrc/sbt-bobby/scala_2.10/sbt_0.13/0.8.1-4-ge733d26/jars/sbt-bobby.jar
//https://bintray.com/api/v1/   content/hmrc/sbt-plugin-releases          /uk.gov.hmrc/sbt-bobby/scala_2.10/sbt_0.13/1.0.0/jars/sbt-bobby.jar
class BintrayIvyPaths() extends PathBuilder {

  val sbtVersion = "sbt_0.13"
  val bintrayRepoRoot = "https://bintray.com/artifact/download/hmrc/"
  val bintrayApiRoot = "https://bintray.com/api/v1/content/hmrc/"

  override def jarFilenameFor(v:VersionDescriptor):String={
    s"${v.artefactName}.jar"
  }

  override def jarUrlFor(v:VersionDescriptor):String={
    val fileName = jarFilenameFor(v)
    s"$bintrayRepoRoot${v.repo}/uk.gov.hmrc/${v.artefactName}/scala_${v.scalaVersion}/${sbtVersion}/${v.version}/jars/$fileName"
  }

  override def jarUploadFor(v:VersionDescriptor):String={
    val fileName = jarFilenameFor(v)
    s"$bintrayApiRoot${v.repo}/uk.gov.hmrc/${v.artefactName}/scala_${v.scalaVersion}/$sbtVersion/${v.version}/jars/$fileName"
  }

  override def pomUploadFor(v: VersionDescriptor): String = ???

  override def pomFilenameFor(v: VersionDescriptor): String = ???

  override def pomUrlFor(v: VersionDescriptor): String = ???
}

//https://bintray.com/artifact/download/hmrc/releases/uk/gov/hmrc/http-verbs_2.11/1.4.0/http-verbs_2.11-1.4.0.jar
class BintrayMavenPaths() extends PathBuilder{

  val bintrayRepoRoot = "https://bintray.com/artifact/download/hmrc/"
  val bintrayApiRoot = "https://bintray.com/api/v1/maven/hmrc/"

  def jarFilenameFor(v:VersionDescriptor):String={
    s"${v.artefactName}_${v.scalaVersion}-${v.version}.jar"
  }

  def jarUrlFor(v:VersionDescriptor):String={
    val fileName = jarFilenameFor(v)
    s"$bintrayRepoRoot${v.repo}/uk/gov/hmrc/${v.artefactName}_${v.scalaVersion}/${v.version}/$fileName"
  }

  def jarUploadFor(v:VersionDescriptor):String={
    val fileName = jarFilenameFor(v)
    s"$bintrayApiRoot${v.repo}/${v.artefactName}/uk/gov/hmrc/${v.artefactName}_${v.scalaVersion}/${v.version}/$fileName"
  }

  def pomFilenameFor(v: VersionDescriptor) = s"${v.artefactName}_${v.scalaVersion}-${v.version}.pom"

  def pomUploadFor(v: VersionDescriptor): String={
    val fileName = pomFilenameFor(v)
    s"$bintrayApiRoot${v.repo}/${v.artefactName}/uk/gov/hmrc/${v.artefactName}_${v.scalaVersion}/${v.version}/$fileName"
  }

  def pomUrlFor(v: VersionDescriptor): String = {
    val fileName = pomFilenameFor(v)
    s"$bintrayRepoRoot${v.repo}/uk/gov/hmrc/${v.artefactName}_${v.scalaVersion}/${v.version}/$fileName"
  }
}
