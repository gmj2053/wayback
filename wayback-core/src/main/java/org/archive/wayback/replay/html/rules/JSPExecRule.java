/*
 *  This file is part of the Wayback archival access software
 *   (http://archive-access.sourceforge.net/projects/wayback/).
 *
 *  Licensed to the Internet Archive (IA) by one or more individual 
 *  contributors. 
 *
 *  The IA licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.archive.wayback.replay.html.rules;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;

import org.archive.wayback.replay.JSPExecutor;
import org.archive.wayback.replay.html.ReplayParseContext;
import org.htmlparser.Node;

public class JSPExecRule {
	private String jspPath = null;
	
	public void emit(ReplayParseContext context, Node node) throws ServletException, IOException {
		JSPExecutor jspExec = context.getJspExec();
		if(jspExec != null) {
			OutputStream os = context.getOutputStream();
			if(os != null) {
				String jspResult = jspExec.jspToString(jspPath);
				byte[] bytes = null;
				try {
					bytes = jspResult.getBytes(context.getOutputCharset());
				} catch(UnsupportedEncodingException e) {
					e.printStackTrace();
					bytes = jspResult.getBytes();
				}
				os.write(bytes);
			}
		}
	}

	/**
	 * @return the jspPath
	 */
	public String getJspPath() {
		return jspPath;
	}

	/**
	 * @param jspPath the jspPath to set
	 */
	public void setJspPath(String jspPath) {
		this.jspPath = jspPath;
	}
	
}
