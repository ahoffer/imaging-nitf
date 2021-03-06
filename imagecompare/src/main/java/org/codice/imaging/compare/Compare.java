/*
 * Copyright (c) 2016, Codice
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
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
package org.codice.imaging.compare;

import java.awt.image.BufferedImage;

/**
 * Utilities for image comparison.
 */
public final class Compare {

    private static final int RGB_MASK = 0xFFFFFF;

    private Compare() {
    }

    /**
     * Check whether two images are the same while only considering specific non-padded region and ignore alpha values.
     *
     * @param imageUnderTest the image to check
     * @param referenceImage the reference image
     * @param nonPadWidth    the width to consider for comparison
     * @param nonPadHeight   the height to consider for comparison
     * @return true if the images are identical in size and content.
     */
    public static boolean areIdentical(final BufferedImage imageUnderTest,
            final BufferedImage referenceImage, final int nonPadWidth, final int nonPadHeight) {
        if (imageUnderTest.getWidth() != referenceImage.getWidth()) {
            return false;
        }
        if (imageUnderTest.getHeight() != referenceImage.getHeight()) {
            return false;
        }
        for (int x = 0; x < nonPadWidth; ++x) {
            for (int y = 0; y < nonPadHeight; ++y) {
                int iutPixel = imageUnderTest.getRGB(x, y);
                int refPixel = referenceImage.getRGB(x, y);
                if (iutPixel != refPixel && ((iutPixel & RGB_MASK) != (refPixel & RGB_MASK))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check whether two images are the same.
     * @param imageUnderTest the image to check
     * @param referenceImage the reference image
     * @return true if the images are identical in size and content.
     */
    public static boolean areIdentical(final BufferedImage imageUnderTest, final BufferedImage referenceImage) {
        if (imageUnderTest.getWidth() != referenceImage.getWidth()) {
            return false;
        }
        if (imageUnderTest.getHeight() != referenceImage.getHeight()) {
            return false;
        }
        for (int x = 0; x < referenceImage.getWidth(); ++x) {
            for (int y = 0; y < referenceImage.getHeight(); ++y) {
                int iutPixel = imageUnderTest.getRGB(x, y);
                int refPixel = referenceImage.getRGB(x, y);
                if (iutPixel != refPixel) {
                    return false;
                }
            }
        }
        return true;
    }
}
