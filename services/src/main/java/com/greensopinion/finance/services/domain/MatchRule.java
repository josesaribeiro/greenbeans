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
package com.greensopinion.finance.services.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.regex.Pattern;

import com.google.common.base.MoreObjects;

public class MatchRule {

	private static final String REGEX_MULTIPLE_SPACES = "\\s+";
	private final String pattern;
	private transient Pattern $pattern;

	public static MatchRule withPattern(String pattern) {
		return new MatchRule(pattern);
	}

	public static MatchRule withPatternFromText(String matchDescription) {
		String pattern = "";
		for (char c : matchDescription.trim().toCharArray()) {
			if (Character.isLetterOrDigit(c)) {
				pattern += c;
			} else if (Character.isWhitespace(c)) {
				if (!pattern.endsWith(REGEX_MULTIPLE_SPACES)) {
					pattern += REGEX_MULTIPLE_SPACES;
				}
			} else {
				pattern += Pattern.quote(Character.toString(c));
			}
		}
		return withPattern(pattern);
	}

	String getPattern() {
		return pattern;
	}

	private Pattern pattern() {
		if ($pattern == null) {
			$pattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		}
		return $pattern;
	}

	private MatchRule(String regex) {
		this.pattern = checkNotNull(regex);
	}

	public boolean matches(Transaction transaction) {
		checkNotNull(transaction);
		return pattern().matcher(transaction.getDescription()).find();
	}

	@Override
	public int hashCode() {
		return pattern.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		MatchRule other = (MatchRule) obj;
		return pattern.equals(other.pattern);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(MatchRule.class).add("pattern", pattern).toString();
	}
}
