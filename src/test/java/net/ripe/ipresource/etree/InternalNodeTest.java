/**
 * The BSD License
 *
 * Copyright (c) 2010, 2011 RIPE NCC
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *   - Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *   - Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *   - Neither the name of the RIPE NCC nor the names of its contributors may be
 *     used to endorse or promote products derived from this software without
 *     specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package net.ripe.ipresource.etree;

import static org.junit.Assert.*;

import net.ripe.ipresource.etree.InternalNode;

import org.junit.Before;
import org.junit.Test;

public class InternalNodeTest {

    private InternalNode<TestInterval, String> a = new InternalNode<TestInterval, String>(new TestInterval(1, 2), "1-2");
    private InternalNode<TestInterval, String> b = new InternalNode<TestInterval, String>(new TestInterval(1, 2), "1-2");
    private InternalNode<TestInterval, String> c = new InternalNode<TestInterval, String>(new TestInterval(1, 4), "1-4");
    private InternalNode<TestInterval, String> d = new InternalNode<TestInterval, String>(new TestInterval(1, 4), "1-4");
    private InternalNode<TestInterval, String> e = new InternalNode<TestInterval, String>(new TestInterval(2, 5), "2-5");

    @Before
    public void setup() {
        d.addChild(a);
    }

    @Test
    public void test_equals_and_hashcode() {
        assertFalse(a.equals(null));
        assertFalse(a.equals(new Object()));
        assertEquals(a, a);
        assertEquals(a, b);
        assertEquals(c, c);
        assertFalse(a.equals(c));
        assertFalse(c.equals(a));
        assertFalse(c.equals(d));

        assertEquals(a.hashCode(), a.hashCode());
        assertEquals(a.hashCode(), b.hashCode());
        assertFalse(a.hashCode() == c.hashCode());
        assertFalse(c.hashCode() == d.hashCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_overlap_insert_fails() {
        c.addChild(e);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_overlap_remove_fails() {
        c.removeChild(e.getInterval());
    }

}