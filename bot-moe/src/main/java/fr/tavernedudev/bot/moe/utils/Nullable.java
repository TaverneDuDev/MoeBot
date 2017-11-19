/*
 * This file is part of the Moe-Bot package
 * Copyright (c) 2017. Sébastien Vermeille <sebastien.vermeille@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package fr.tavernedudev.bot.moe.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Nullable annotation
 *
 * Indicate when a parameter can be null without causing any problems
 *
 * @author Sébastien Vermeille <sebastien.vermeille@gmail.com>
 */

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.PARAMETER)
public @interface Nullable {
}
