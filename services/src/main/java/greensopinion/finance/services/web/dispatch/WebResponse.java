/*******************************************************************************
 * Copyright (c) 2015, 2016 David Green.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package greensopinion.finance.services.web.dispatch;

public class WebResponse {

	private int responseCode;
	private String entity;

	public WebResponse(int responseCode, String entity) {
		this.responseCode = responseCode;
		this.entity = entity;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public String getEntity() {
		return entity;
	}
}