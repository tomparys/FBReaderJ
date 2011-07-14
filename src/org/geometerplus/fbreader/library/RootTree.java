/*
 * Copyright (C) 2009-2011 Geometer Plus <contact@geometerplus.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

package org.geometerplus.fbreader.library;

import org.geometerplus.zlibrary.core.resources.ZLResource;

public class RootTree extends LibraryTree {
	private final Library myLibrary;
	private final String myId;
	private final ZLResource myResource;

	RootTree(Library library, String id) {
		myLibrary = library;
		myId = id;
		myResource = Library.resource().getResource(myId);
	}

	@Override
	public String getName() {
		return myResource.getValue();
	}

	@Override
	public String getTreeTitle() {
		return getSecondString();
	}

	@Override
	protected String getSummary() {
		return myResource.getResource("summary").getValue();
	}

	@Override
	protected String getStringId() {
		return myId;
	}

	@Override
	public Status getOpeningStatus() {
		return
			myLibrary.hasState(Library.STATE_FULLY_INITIALIZED)
				? Status.READY_TO_OPEN
				: Status.WAIT_FOR_OPEN;
	}

	@Override
	public String getOpeningStatusMessage() {
		return "loadingBookList";
	}

	@Override
	public void waitForOpening() {
		myLibrary.waitForState(Library.STATE_FULLY_INITIALIZED);
	}

	@Override
	public boolean isSelectable() {
		return false;
	}
}
