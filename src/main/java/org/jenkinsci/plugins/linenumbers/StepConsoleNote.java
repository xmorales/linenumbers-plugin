/*
 * The MIT License
 * 
 * Copyright (c) 2014, Vincent Latombe
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jenkinsci.plugins.linenumbers;

import hudson.MarkupText;
import hudson.console.ConsoleAnnotator;
import hudson.console.ConsoleNote;

public class StepConsoleNote<T> extends ConsoleNote<T> {
  public enum Kind {
    BUILDSTEP_START,
    BUILDSTEP_END
  }

  private Kind kind;

  private int  counter;

  public StepConsoleNote(Kind kind, int counter) {
    this.kind = kind;
    this.counter = counter;
  }

  @Override
  public ConsoleAnnotator annotate(T context, MarkupText text, int charPos) {
    int end = text.length() - 1;
    if (kind == Kind.BUILDSTEP_START) {
      String foldName = "buildstep." + counter;
      text.addMarkup(0, end, "<!--linenumbers_start1--><div class=\"fold-start fold\" id=\"fold-start-" + foldName + "\"><span class=\"fold-name\">" + foldName + "</span>", "<!--linenumbers_start2-->");
    } else {
      
      text.addMarkup(0, end, "<!--linenumbers_end1-->", "</div><!--linenumbers_end2-->");
    }
    return null;
  }

}
