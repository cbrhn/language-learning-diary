import {
    IconAdjustments,
    IconCalendarStats,
    IconFileAnalytics,
    IconGauge,
    IconNotes,
} from '@tabler/icons-react';
import { ScrollArea } from '@mantine/core';
import { LinksGroup } from './NavbarLinksGroup';
import { UserButton } from './UserButton';
import { Logo } from './Logo';
import classes from './NavbarNested.module.css';

const mockdata = [
    { label: 'Dashboard', icon: IconGauge },
    { label: 'Quick Logger', icon: IconGauge },
    {
        label: 'Library',
        icon: IconNotes,
        links: [
            { label: 'Item 1', link: '/' },
            { label: 'Item 2', link: '/' },
        ],
    },
    {
        label: 'Diaries',
        icon: IconCalendarStats,
        links: [
            { label: 'Data Test', link: '/' },
            { label: 'Item 2', link: '/' },
            { label: 'Item 3', link: '/' },
        ],
    },
    { label: 'Stats', icon: IconFileAnalytics },
    { label: 'Settings', icon: IconAdjustments },

];

export function NavbarNested() {
    const links = mockdata.map((item) => <LinksGroup {...item} key={item.label} />);

    return (
        <nav className={classes.navbar}>
            <Logo style={{ width: 150 }} />  {/* tmp, TODO: replace icon */}

            <ScrollArea className={classes.links}>
                <div className={classes.linksInner}>{links}</div>
            </ScrollArea>

            <div className={classes.footer}>
                <UserButton />
            </div>
        </nav>
    );
}