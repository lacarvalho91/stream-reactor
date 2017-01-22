/*
 * *
 *   * Copyright 2016 Datamountaineer.
 *   *
 *   * Licensed under the Apache License, Version 2.0 (the "License");
 *   * you may not use this file except in compliance with the License.
 *   * You may obtain a copy of the License at
 *   *
 *   * http://www.apache.org/licenses/LICENSE-2.0
 *   *
 *   * Unless required by applicable law or agreed to in writing, software
 *   * distributed under the License is distributed on an "AS IS" BASIS,
 *   * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   * See the License for the specific language governing permissions and
 *   * limitations under the License.
 *   *
 */

package com.datamountaineer.streamreactor.connect.hazelcast.writers

import com.datamountaineer.streamreactor.connect.hazelcast.config.HazelCastSinkSettings
import com.hazelcast.core.{HazelcastInstance, IMap}
import org.apache.kafka.connect.sink.SinkRecord

/**
  * Created by andrew@datamountaineer.com on 02/12/2016. 
  * stream-reactor
  */
case class MapWriter(client: HazelcastInstance, topic: String, settings: HazelCastSinkSettings) extends Writer(settings) {
  val mapWriter = client.getMap(settings.topicObject(topic).name).asInstanceOf[IMap[String, Object]]

  override def write(record: SinkRecord): Unit = mapWriter.put(buildPKs(record), convert(record))
  override def close: Unit = {}
}