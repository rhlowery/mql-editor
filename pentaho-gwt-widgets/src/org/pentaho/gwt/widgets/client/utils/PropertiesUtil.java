/*
 * This program is free software; you can redistribute it and/or modify it under the 
 * terms of the GNU Lesser General Public License, version 2.1 as published by the Free Software 
 * Foundation.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this 
 * program; if not, you can obtain a copy at http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html 
 * or from the Free Software Foundation, Inc., 
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * Copyright 2008 Pentaho Corporation.  All rights reserved.
 */
package org.pentaho.gwt.widgets.client.utils;

import java.util.HashMap;

public class PropertiesUtil {

  /**
   * This method builds a HashMap out of a given input String (which is typically read from a standard
   * java .properties file).  The caller may provide a default properties map which will be 'merged' with the
   * new properties.  When a collision occurs, the new values always win.
   * 
   * @param text
   *          This is typically the contents of a .properties file in String form
   * @param defaultProperties
   *          A map of default settings which will be overridden if they exist in the provided input
   * @return name/value pairs for each name=value in the .properties file
   */
  public static HashMap<String, String> buildProperties(String text, HashMap<String, String> defaultProperties) {
    // we're going to override existing settings, in this way we can override default values with locale
    // specific variants for example, we do not want to modify the user provided list
    HashMap<String, String> settings = new HashMap<String, String>();
    // add defaults to new list
    if (defaultProperties != null) {
      settings.putAll(defaultProperties);
    }
    StringTokenizer lineTokenizer = new StringTokenizer(text, '\n');
    for (int i = 0; i < lineTokenizer.countTokens(); i++) {
      String line = lineTokenizer.tokenAt(i);
      if (line.indexOf('=') != -1) {
        StringTokenizer settingTokenizer = new StringTokenizer(lineTokenizer.tokenAt(i), '=');
        String key = settingTokenizer.tokenAt(0).trim();
        String value = settingTokenizer.tokenAt(1).trim();
        settings.put(key, value);
      }
    }
    return settings;
  }

  /**
   * This method builds a HashMap out of a given input String (which is typically read from a standard
   * java .properties file).
   * 
   * @param text
   *          This is typically the contents of a .properties file in String form
   * @return name/value pairs for each name=value in the .properties file
   */
  public static HashMap<String, String> buildProperties(String text) {
    return buildProperties(text, null);
  }

}
