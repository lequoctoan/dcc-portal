/*
 * Copyright 2016(c) The Ontario Institute for Cancer Research. All rights reserved.
 *
 * This program and the accompanying materials are made available under the terms of the GNU Public
 * License v3.0. You should have received a copy of the GNU General Public License along with this
 * program. If not, see <http://www.gnu.org/licenses/>.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

(function () {
   'use strict';

   var module = angular.module('icgc.software', []);

   module.config(function ($stateProvider, PortalFeatureProvider) {
      $stateProvider.state('software', {
         url: '/software',
         templateUrl: function() {
           var templateURL = '',
               isSoftwarePageEnabled = PortalFeatureProvider.hasFeature('SOFTWARE_PAGE') !== false;

           if (isSoftwarePageEnabled) {
              templateURL = 'scripts/software/views/software.html';
           }

           return templateURL;
         },
         controller: 'SoftwareController'
      });
   });

   module.controller('SoftwareController', function($scope, Page) {
      Page.stopWork();
      Page.setPage('entity');
      Page.setTitle('Software Downloads');

      $scope.icgcStorageClient = {
    		  artifactId: 'icgc-storage-client'
      };
      $scope.icgcGet = {
    		  artifactId:'icgc-get'
      };
      jQuery.get('api/v1/ui/software/' + $scope.icgcStorageClient[artifactId] + '/versions', function(versions) {
         $scope.versions = versions;
      });
      jQuery.get('api/v1/ui/software/' + $scope.icgcGet[artifactId] + '/versions', function(versions) {
          $scope.icgcGetVersions = versions;
       });
   });

})();
