/*
 * Copyright (c) 2021, 2021, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package com.oracle.graal.reachability.summaries;

import com.oracle.graal.pointsto.meta.AnalysisMethod;
import com.oracle.graal.reachability.MethodSummary;
import com.oracle.graal.reachability.SerializableMethodSummary;

import java.util.Arrays;

public class DummyHashingStrategy implements HashingStrategy {
    @Override
    public PersistedSummary prepare(AnalysisMethod method, MethodSummary summary) {
        long hash = getHash(method);
        return new PersistedSummary(summary, new MethodHash(hash));
    }

    @Override
    public boolean isValid(AnalysisMethod method, SerializableMethodSummary summary) {
        return summary.hash.getValue() == getHash(method);
    }

    private static long getHash(AnalysisMethod method) {
        return Arrays.hashCode(method.getCode());
    }
}