/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.richfaces.fragment.popupPanel;

import org.richfaces.fragment.panel.Panel;

/**
 * @author <a href="mailto:jstefek@redhat.com">Jiri Stefek</a>
 */
public interface PopupPanel<HEADER, HEADERCONTROLS, BODY> extends Panel<HEADER, BODY> {

    /**
     * Gets the controls of the popup panel. They are user defined controls usually to close popup panel.
     *
     * @return the object representing popup panel controls
     */
    HEADERCONTROLS getHeaderControlsContent();

    /**
     * Represents the direction where the popup panel should be resized.
     *
     * @author <a href="mailto:jstefek@redhat.com">Jiri Stefek</a>
     *
     */
    enum ResizerLocation {

        N, E, S, W, NE, NW, SE, SW
    }
}
