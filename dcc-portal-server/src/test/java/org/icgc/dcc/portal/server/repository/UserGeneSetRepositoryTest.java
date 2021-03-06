/*
 * Copyright (c) 2014 The Ontario Institute for Cancer Research. All rights reserved.                             
 *                                                                                                               
 * This program and the accompanying materials are made available under the terms of the GNU Public License v3.0.
 * You should have received a copy of the GNU General Public License along with                                  
 * this program. If not, see <http://www.gnu.org/licenses/>.                                                     
 *                                                                                                               
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY                           
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES                          
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT                           
 * SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,                                
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED                          
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;                               
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER                              
 * IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN                         
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.icgc.dcc.portal.server.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.icgc.dcc.portal.server.test.Tests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lombok.val;

public class UserGeneSetRepositoryTest {

  UserGeneSetRepository repository;

  @Before
  public void setUp() {
    val dbi = Tests.createDBI();
    this.repository = dbi.open(UserGeneSetRepository.class);
  }

  @After
  public void tearDown() {
    repository.close();
  }

  @Test
  public void testAll() {
    val id1 = UUID.randomUUID();
    val data1 = "data2";
    val count1 = repository.save(id1, data1);
    assertThat(count1).isEqualTo(1);

    val id2 = UUID.randomUUID();
    val data2 = "data2";
    val count2 = repository.save(id2, data2);
    assertThat(count2).isEqualTo(1);

    assertThat(id1).isNotEqualTo(id2);

    val actualData1 = repository.find(id1);
    assertThat(data1).isEqualTo(actualData1);

    val actualData2 = repository.find(id2);
    assertThat(data2).isEqualTo(actualData2);
  }

}
